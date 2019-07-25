# Joystick


## Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

## Step 2. Add the dependency
```
dependencies {
	        implementation 'com.github.Gredicer:Joystick:1.1.0'
	}
```


## How to use 

1.Add this code to your XML file
```
<com.e.virtualjoystick.Joystick
        android:layout_width="200dp"
        android:layout_height="200dp"
        android:id="@+id/joystick"></com.e.virtualjoystick.Joystick>
```
2.And setting the circle button radius
```
Joystick joystick=findViewById( R.id.joystick );
        joystick.setCircleR( 30 );
```

[![](https://jitpack.io/v/Gredicer/Joystick.svg)](https://jitpack.io/#Gredicer/Joystick)



