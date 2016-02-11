package cs544.myblog.web.config;

/**
 *
 * @author bikesh
 */
public class ApplicationMenu {

    private String code;
    private String label;
    private String parentCode;
    private String url;
    private String permissionCode;
    private MenuType menuType;

    public enum MenuType {

        Parameters,
        Entries,
        Queries,
        Verifications,
        Root
    }

    public ApplicationMenu() {
        this.menuType = MenuType.Root;
    }

    public ApplicationMenu(String code, String label, String parentCode) {
        this();
        this.code = code;
        this.label = label;
        this.parentCode = parentCode;
    }


    public ApplicationMenu(String code, String label, String parentCode, String url, String permissionCode, MenuType menuType) {
        this.code = code;
        this.label = label;
        this.parentCode = parentCode;
        this.url = url;
        this.permissionCode = permissionCode;
        this.menuType = menuType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public void setMenuType(MenuType menuType) {
        this.menuType = menuType;
    }

    @Override
    public String toString() {
        return "ApplicationMenu{" + "code=" + code + ", label=" + label + ", parentCode=" + parentCode + ", url=" + url + ", permissionCode=" + permissionCode + ", menuType=" + menuType + '}';
    }

}
