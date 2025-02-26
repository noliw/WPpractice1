package com.nolawiworkineh.wppractice1

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MukundApplication : Application()

// required by hilt for DI
// it acts as the root class as sets up the dependency graph
// if we dont annotate it hilt will throw an error

// entendind the application class ensures global state meaning that the application will
// live as long as the application is alive
// and makes sure that dependencies are initialized before any activity