
package org.picfight.dcr.code.process;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FilesList;
import com.jfixby.scarabei.api.file.LocalFile;
import com.jfixby.scarabei.api.file.LocalFileSystem;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.red.desktop.ScarabeiDesktop;

public class ReplaceAllDcrGoDeps {

	public static void main (final String[] args) throws IOException {
		ScarabeiDesktop.deploy();

		final String token = "github.com/decred/";
		final String replacement = "github.com/picfight/";

		final String targetPath = "D:\\PICFIGHT\\src\\github.com\\picfight\\pfcd";
		final LocalFile target = LocalFileSystem.newFile(targetPath);
		final FilesList goFiles = target.listAllChildren(file -> {
			try {
				return (file.extensionIs("go") || file.extensionIs("md")) && file.readToString().contains(token);
			} catch (final IOException e) {
				e.printStackTrace();
				return false;
			}
		});

		L.d("go files", goFiles);
		final Map<String, String> replacers = Collections.newMap();

		for (final File f : goFiles) {
			final String data = f.readToString();
			final String newData = data.replaceAll(token, replacement);
			L.d("write", f);
// f.writeString(newData);
		}
	}

}
