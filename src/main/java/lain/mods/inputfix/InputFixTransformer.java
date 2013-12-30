package lain.mods.inputfix;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;

public class InputFixTransformer implements IClassTransformer
{

    class a extends ClassVisitor
    {

        Set<String> names;
        String cl;

        public a(ClassVisitor cv)
        {
            super(Opcodes.ASM4, cv);
            cl = FMLDeobfuscatingRemapper.INSTANCE.unmap("net/minecraft/client/gui/GuiScreen");
            names = new HashSet<String>();
            names.add(InputFix.RUNTIME_DEOBF ? "func_146282_l" : "handleKeyboardInput");
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
        {
            if (names.contains(FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(cl, name, desc)) && "()V".equals(desc))
            {
                MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
                mv.visitCode();
                mv.visitVarInsn(Opcodes.ALOAD, 0);
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "lain/mods/inputfix/GuiScreenFix", "handleKeyboardInput", "(Lnet/minecraft/client/gui/GuiScreen;)V");
                mv.visitInsn(Opcodes.RETURN);
                mv.visitMaxs(1, 1);
                mv.visitEnd();
                return new b();
            }
            return super.visitMethod(access, name, desc, signature, exceptions);
        }

    }

    class b extends MethodVisitor
    {

        public b()
        {
            super(Opcodes.ASM4, null);
        }

    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes)
    {
        if ("net.minecraft.client.gui.GuiScreen".equals(transformedName))
            return transform001(bytes);
        return bytes;
    }

    private byte[] transform001(byte[] bytes)
    {
        ClassReader classReader = new ClassReader(bytes);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        classReader.accept(new a(classWriter), ClassReader.EXPAND_FRAMES);
        return classWriter.toByteArray();
    }

}
