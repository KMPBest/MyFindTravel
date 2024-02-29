import SwiftUI
import shared


class AppDelegate: NSObject, UIApplicationDelegate {

 func application(_ application: UIApplication,
                  didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {

   AppInitializer.shared.initialize(isDebug: true, onKoinStart: { _ in })

   return true

 }
}

@main
struct iOSApp: App {
   @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
