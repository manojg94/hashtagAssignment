package com.manoj.hashtagassignment.api.Adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class type extends ArrayList<type> {


    public type(int initialCapacity, List subnames) {
        super(initialCapacity);
        this.subnames = subnames;
    }

    public type(List subnames) {
        this.subnames = subnames;
    }

    public type(Collection<? extends type> c, List subnames) {
        super(c);
        this.subnames = subnames;
    }

    public List getSubnames() {
        return subnames;
    }

    public void setSubnames(List subnames) {
        this.subnames = subnames;
    }

    private List subnames;
}
