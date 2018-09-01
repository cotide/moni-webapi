package com.gold.moni.helper.fastjson;

import java.util.HashSet;
import java.util.Set;
import com.alibaba.fastjson.serializer.PropertyFilter;

public class FastjsonFilterUtil implements PropertyFilter {


    /** 包含的属性 */
    private final Set<String> includes = new HashSet<String>();
    /** 排除的属性 */
    private final Set<String> excludes = new HashSet<String>();

    public Set<String> getIncludes() {
        return includes;
    }

    public Set<String> getExcludes() {
        return excludes;
    }

    @Override
    public boolean apply(Object source, String name, Object value) {
        if (excludes.contains(name)) {
            return false;
        }
        if (excludes.contains(source.getClass().getSimpleName() + "." + name)) {
            return false;
        }
        if (includes.size() == 0 || includes.contains(name)) {
            return true;
        }
        if (includes.contains(source.getClass().getSimpleName() + "." + name)) {
            return true;
        }
        return false;
    }
}
