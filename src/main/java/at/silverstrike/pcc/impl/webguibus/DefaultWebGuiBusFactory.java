package at.silverstrike.pcc.impl.webguibus;

import at.silverstrike.pcc.api.webguibus.WebGuiBus;
import at.silverstrike.pcc.api.webguibus.WebGuiBusFactory;

public class DefaultWebGuiBusFactory implements WebGuiBusFactory {

    @Override
    public WebGuiBus create() {
        return new DefaultWebGuiBus();
    }

}
