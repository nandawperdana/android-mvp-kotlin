# android-mvp-kotlin
Android MVP architecture with Kotlin + Retrofit2 + RxJava2 + Realm
Contains boilerplate (branch: master) and sample app (branch: sample)

## Libraries
In this sample project includes some of useful libraries for Android, such as:
- [Retrofit](http://square.github.io/retrofit/): A type-safe REST client for Android which intelligently maps an API into a client interface using annotations.
- [Moshi](https://github.com/square/moshi): A modern JSON library for Android and Java. It makes it easy to parse JSON into Java objects.
- [Picasso](http://square.github.io/picasso/): A powerful image downloading and caching library for Android.
- [RxJava2](https://github.com/ReactiveX/RxJava): Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM.
- [Realm DB]()

## The Clean Architecture
![clean architecture](https://8thlight.com/blog/assets/posts/2012-08-13-the-clean-architecture/CleanArchitecture-8b00a9d7e2543fa9ca76b81b05066629.jpg)

Before we go through to learn this architecture, I hope you've understand about the **Clean Architecture** (from [Uncle Bob](https://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html). This architecture is the simple combination from those references. So, this architecture makes your code:
- **Independent of Frameworks.**
- **Testable**.
- **Independent of UI.**
- **Independent of Database.**
- **Independent of any external agency.**

## Sample API
This project using sample API from my private API sample.

![screenshoot](https://raw.githubusercontent.com/nandawperdana/android-mvp-kotlin/master/Screenshot_1510674836.png)

## Links, Resources and References
1. https://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html
2. https://android.jlelse.eu/learn-kotlin-while-developing-an-android-app-introduction-567e21ff9664
