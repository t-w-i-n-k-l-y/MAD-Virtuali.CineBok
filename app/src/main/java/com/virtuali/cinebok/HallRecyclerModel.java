/*package com.virtuali.cinebok;

public class HallRecyclerModel {
}
*/


package com.virtuali.cinebok;

import android.widget.AbsListView;

public class HallRecyclerModel {
    String name,description,hurl;

    HallRecyclerModel(){

    }

    public HallRecyclerModel(String name, String description, String hurl) {
        this.name = name;
        this.description = description;
        this.hurl = hurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHurl() {
        return hurl;
    }

    public void setHurl(String hurl) {
        this.hurl = hurl;
    }
}
