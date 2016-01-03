package org.panda_lang.panda.core.syntax.block;

import org.panda_lang.panda.core.Particle;
import org.panda_lang.panda.core.parser.Atom;
import org.panda_lang.panda.core.parser.essential.BlockCenter;
import org.panda_lang.panda.core.parser.essential.ParameterParser;
import org.panda_lang.panda.core.parser.essential.util.BlockInitializer;
import org.panda_lang.panda.core.parser.essential.util.BlockLayout;
import org.panda_lang.panda.core.syntax.Block;
import org.panda_lang.panda.core.syntax.Essence;
import org.panda_lang.panda.core.syntax.Factor;
import org.panda_lang.panda.core.syntax.NamedExecutable;
import org.panda_lang.panda.lang.PThread;

public class ThreadBlock extends Block {

    static {
        BlockCenter.registerBlock(new BlockLayout(ThreadBlock.class, "thread").initializer(new BlockInitializer() {
            @Override
            public Block initialize(Atom atom) {
                Block current = new ThreadBlock();
                Factor[] factors = new ParameterParser().parse(atom, atom.getBlockInfo().getParameters());
                current.setFactors(factors);
                return current;
            }
        }));
    }

    private PThread pThread;

    public ThreadBlock() {
        super.setName("thread::" + System.nanoTime());
    }

    public Essence start(final Particle particle) {
        final Block block = this;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (NamedExecutable executable : block.getExecutables()) {
                    executable.run(particle);
                }
            }
        });
        if (factors != null && factors.length > 0) {
            Essence value = factors[0].getValue();
            thread.setName(pThread.getName());
        }
        thread.start();
        return null;
    }

    @Override
    public Essence run(final Particle particle) {
        if (factors.length == 0) {
            start(particle);
            return null;
        } else {
            pThread = factors[0].getValue(PThread.class);
            pThread.setBlock(this);
            return pThread;
        }
    }

}