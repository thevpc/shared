package tn.corp.mlda.mlda;

import net.thevpc.nuts.NutsApplication;
import net.thevpc.nuts.NutsApplicationContext;
import net.thevpc.nuts.NutsLauncherOptions;
import net.thevpc.nuts.NutsSession;
import net.thevpc.nuts.NutsSupportCondition;
import tn.corp.mlda.mlda.presentation.App;

public class Main implements NutsApplication {
	public static NutsApplicationContext APP_CONTEXT;
	public static void main(String[] args) {
		new Main().runAndExit(args);
	}

	@Override
	public void onInstallApplication(NutsApplicationContext applicationContext) {
		APP_CONTEXT=applicationContext;
		NutsSession session = applicationContext.getSession();
		session.out().println("##MLDA## is an ###amaizing### application!");
		session.out().println("you are installig ##MLDA##");
//		applicationContext.getWorkspace().env()
//				.addLauncher(new NutsLauncherOptions().setAlias("mlda")
//						.setId(applicationContext.getAppId())
//						.setCreateDesktopShortcut(NutsSupportCondition.SUPPORTED)
//						.setCreateMenuShortcut(NutsSupportCondition.SUPPORTED));
	}

	@Override
	public void run(NutsApplicationContext applicationContext) {
		APP_CONTEXT=applicationContext;
		NutsSession session = applicationContext.getSession();
		session.out().println("##MLDA## is an ###amaizing### application!");
		session.out().println("you are running ##MLDA##");
//		applicationContext.getWorkspace().env()
//		.addLauncher(new NutsLauncherOptions()
//				.setAlias("mlda")
//				.setId(applicationContext.getAppId())
//				.setCreateDesktopShortcut(NutsSupportCondition.ALWAYS)
//				.setCreateMenuShortcut(NutsSupportCondition.NEVER)
//				);
		App.run(applicationContext.getArguments());
	}
}
