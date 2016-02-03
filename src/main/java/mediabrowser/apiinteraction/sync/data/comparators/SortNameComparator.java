package mediabrowser.apiinteraction.sync.data.comparators;

import mediabrowser.model.dto.BaseItemDto;

import java.util.Comparator;

/**
 * Created by Luke on 3/23/2015.
 */
public class SortNameComparator implements Comparator<BaseItemDto> {
    @Override
    public int compare(BaseItemDto lhs, BaseItemDto rhs) {
        return getValue(lhs).compareToIgnoreCase(getValue(rhs));
    }

    private String getValue(BaseItemDto item){

        String val = item.getSortName();

        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(val))
        {
            val = item.getName();
        }

        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(val))
        {
            val = "";
        }

        return val;
    }
}
