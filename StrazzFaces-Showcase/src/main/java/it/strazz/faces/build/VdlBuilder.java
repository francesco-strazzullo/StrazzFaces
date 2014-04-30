package it.strazz.faces.build;

import it.strazz.faces.StrazzFaces;

import java.io.File;

import org.omnifaces.vdldoc.VdldocGenerator;

public class VdlBuilder {
	public static void main(String[] args) {
		VdldocGenerator generator = new VdldocGenerator();
		
		generator.setWindowTitle("StrazzFacess v." + StrazzFaces.VERSION); // Else default will be used.
		generator.setDocTitle("StrazzFaces VDL Documentation"); // Else default will be used.
		generator.setOutputDirectory(new File("src\\main\\webapp\\vdldoc"));
		generator.setFacesConfig(new File("..\\StrazzFaces\\src\\META-INF\\faces-config.xml")); // Optional.
		generator.addTaglib(new File("..\\StrazzFaces\\src\\META-INF\\strazzfaces.taglib.xml"));
		
		generator.generate();
	}
}

