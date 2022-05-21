plugins {
  id(Config.Plugins.androidLibrary)
  id(Config.Plugins.kotlinAndroid)
  id(Config.Plugins.kotlinKapt)
}

android {
  compileSdk = Config.AppConfig.compileSdkVersion

  defaultConfig {
    minSdk = Config.AppConfig.minSdkVersion
  }

  buildTypes {
    release {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }
  }

  viewBinding {
    isEnabled = true
  }
}

dependencies {
  implementation(Libraries.lifecycle)
  implementation(Libraries.materialDesign)
}