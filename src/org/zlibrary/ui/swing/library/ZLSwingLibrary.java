package org.zlibrary.ui.swing.library;

import java.io.InputStream;

import org.zlibrary.core.library.ZLibrary;
import org.zlibrary.core.xmlconfig.ZLXMLConfigManager;
import org.zlibrary.core.application.ZLApplication;
import org.zlibrary.core.dialogs.ZLDialogManager;

//import org.zlibrary.core.xml.sax.ZLSaxXMLProcessorFactory;
import org.zlibrary.core.xml.own.ZLOwnXMLProcessorFactory;

import org.zlibrary.ui.swing.view.ZLSwingPaintContext;
import org.zlibrary.ui.swing.application.ZLSwingApplicationWindow;
import org.zlibrary.ui.swing.dialogs.ZLSwingDialogManager;
import org.zlibrary.ui.swing.image.ZLSwingImageManager;

public class ZLSwingLibrary extends ZLibrary {
	public ZLSwingPaintContext createPaintContext() {
		return new ZLSwingPaintContext();
	}

	public InputStream getResourceInputStream(String fileName) {
		return getClass().getClassLoader().getResourceAsStream(fileName);
	}

	public static void shutdown() {
		ZLXMLConfigManager.release();
		System.exit(0);
	}

	@SuppressWarnings("unchecked")
	public void run(String[] args) {
		//new ZLSaxXMLProcessorFactory();
		new ZLOwnXMLProcessorFactory();
		loadProperties();

		new ZLXMLConfigManager(System.getProperty("user.home") + "/." + getInstance().getApplicationName());
		new ZLSwingImageManager();
		new ZLSwingDialogManager();

		ZLApplication application = null;
		try {
			application = (ZLApplication)getApplicationClass().getConstructor(String[].class).newInstance(new Object[] { args });
		} catch (Exception e) {
			e.printStackTrace();
			shutdown();
		}

		ZLSwingApplicationWindow mainWindow =
			((ZLSwingDialogManager)ZLSwingDialogManager.getInstance()).createApplicationWindow(application);
		application.initWindow();
		mainWindow.run();
		
//		ZLDialogManager.getInstance().errorBox(new "noHelpBox");
	}
}
