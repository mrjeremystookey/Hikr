package r.stookey.hikr.di

class Injector private constructor(){

    companion object {
        fun get(): HikrComponent = HikrApp.get().component
    }
}