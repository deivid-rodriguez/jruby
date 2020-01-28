package org.jruby.ir.targets;

import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;

import static org.jruby.util.CodegenUtils.params;
import static org.jruby.util.CodegenUtils.sig;

class IndyYieldCompiler implements YieldCompiler {
    private IRBytecodeAdapter compiler;

    public IndyYieldCompiler(IRBytecodeAdapter compiler) {
        this.compiler = compiler;
    }

    @Override
    public void yield(boolean unwrap) {
        compiler.adapter.invokedynamic("yield", sig(JVM.OBJECT, params(ThreadContext.class, Block.class, JVM.OBJECT)), YieldSite.BOOTSTRAP, unwrap ? 1 : 0);
    }

    @Override
    public void yieldSpecific() {
        compiler.adapter.invokedynamic("yieldSpecific", sig(JVM.OBJECT, params(ThreadContext.class, Block.class)), YieldSite.BOOTSTRAP, 0);
    }

    @Override
    public void yieldValues(int arity) {
        compiler.adapter.invokedynamic("yieldValues", sig(JVM.OBJECT, params(ThreadContext.class, Block.class, JVM.OBJECT, arity)), YieldSite.BOOTSTRAP, 0);
    }
}