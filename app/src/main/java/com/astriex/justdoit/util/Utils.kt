package com.astriex.justdoit.util


// extension property that returns the same object (transforms a statement into an expression)
// gives compile time safety in when block
val <T> T.exhaustive: T
    get() = this