package org.wanne.gwt;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import org.wanne.game.WanneGame;

/** Launches the GWT application. */
public class GwtLauncher extends GwtApplication {
    @Override
    public GwtApplicationConfiguration getConfig () {
        return new GwtApplicationConfiguration(1024, 768);
    }

    @Override
    public ApplicationListener createApplicationListener () {
//        throw new GdxRuntimeException("Kotlin is currently not supported by GWT.");
         return new WanneGame();
    }
}
