package vandavv.views;

import io.dropwizard.views.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddView extends View {
    private final static Logger LOG = LoggerFactory.getLogger(AddView.class);

    public AddView() {
        super("add.mustache");
        LOG.debug("Created AddView");
    }
}
