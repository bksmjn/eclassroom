package cs544.myblog.web.utils;

import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author bikesh
 */
public class UIHelperUtils {

    public static <T> SelectItem[] toArrayOfSelectItem(List<T> obj) {
        SelectItem[] items = new SelectItem[obj.size()];
        int index = 0;
        for (T r : obj) {
            items[index] = new SelectItem(r);
            index++;
        }
        return items;
    }
}
