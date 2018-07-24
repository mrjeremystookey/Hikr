package r.stookey.hikr.di

import r.stookey.hikr.HikrApp

class Injector private constructor(){

    companion object {
        fun get(): HikrComponent = HikrApp.get().component
    }
}