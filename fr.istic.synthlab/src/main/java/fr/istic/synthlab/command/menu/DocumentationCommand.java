package fr.istic.synthlab.command.menu;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import fr.istic.synthlab.Synthlab;
import fr.istic.synthlab.command.ICommand;

public class DocumentationCommand implements ICommand {

	@Override
	public void execute() {
		if (Desktop.isDesktopSupported()) {
		    try {
		        File pdfDocument = new File(ClassLoader.getSystemResource(Synthlab.DOCUMENTATION_PDF_FILE).getFile());
		        Desktop.getDesktop().open(pdfDocument);
		    } catch (IOException ex) {
		        System.err.println("Can't locate documentation file !");
			}
		}
	}

}
