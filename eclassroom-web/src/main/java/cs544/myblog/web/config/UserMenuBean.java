package cs544.myblog.web.config;

import cs544.myblog.web.StartUpBean;
import cs544.myblog.web.usermgmt.control.UserFinder;
import cs544.myblog.web.usermgmt.entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author bikesh
 */
@Controller
@Scope("prototype")
public class UserMenuBean implements Serializable {

    private MenuModel menuModel;
    private List<ApplicationMenu> menus;
    @Autowired
    private UserFinder userFinder;

    @PostConstruct
    private void init() {
        this.menuModel = new DefaultMenuModel();
        this.menus = new ArrayList<>();
        User u = this.userFinder.findUserByEmailAddress(StartUpBean.getUserName());
        System.out.println("ROLE:" + StartUpBean.getRole());
        if (u.getRole().equals("ROLE_ADMIN")) {
            this.addTopLevelMenuForADMIN();

        } else {
            this.addTopLevelMenuForProfessor();
        }
//    this.addTopLevelMenu();
        this.initializeMenu();

    }

    private void initializeMenu() {
        List<ApplicationMenu> topLevelMenus = this.findAllTopLevelApplicationMenu(ApplicationMenu.MenuType.Root);
        for (ApplicationMenu app : topLevelMenus) {
            DefaultSubMenu rootLevelMenu = new DefaultSubMenu(app.getLabel());
            rootLevelMenu.setStyle("border: none;");

            //For entries
            DefaultSubMenu entriesSubMenu = new DefaultSubMenu("Entries");
            rootLevelMenu.addElement(entriesSubMenu);
            List<ApplicationMenu> entries_menus = this.findApplicationMenuByTypeAndParenCode(ApplicationMenu.MenuType.Entries, app.getCode());
            for (ApplicationMenu menu : entries_menus) {
                entriesSubMenu.addElement(new DefaultMenuItem(menu.getLabel(), "ui-icon-pencil", menu.getUrl()));
            }

            //For queries           
            DefaultSubMenu queriesSubMenu = new DefaultSubMenu("Queries");
            rootLevelMenu.addElement(queriesSubMenu);
            List<ApplicationMenu> queries_menus = this.findApplicationMenuByTypeAndParenCode(ApplicationMenu.MenuType.Queries, app.getCode());
            for (ApplicationMenu menu : queries_menus) {
                queriesSubMenu.addElement(new DefaultMenuItem(menu.getLabel(), "ui-icon-help", menu.getUrl()));
            }
            this.menuModel.addElement(rootLevelMenu);
        }
    }

    private void addTopLevelMenuForADMIN() {
        String[] menu_codes = {"User", "Preferences"};
        String[] menu_levels = {"User Management", "Preferences"};
        int index = 0;
        for (String s : menu_codes) {
            this.menus.add(new ApplicationMenu(s, menu_levels[index], null));
            index++;
        }
        this.addSubMenuForADMIN(menu_codes);
    }

    private void addSubMenuForADMIN(String[] menu_codes) {
        this.addUserStructure(menu_codes[0]);
        this.addPreference(menu_codes[1]);
    }

    private void addTopLevelMenuForProfessor() {
        String[] menu_codes = {"Chapters", "Preferences"};
        String[] menu_levels = {"Chapters", "Preferences"};
        int index = 0;
        for (String s : menu_codes) {
            this.menus.add(new ApplicationMenu(s, menu_levels[index], null));
            index++;
        }
        this.addSubMenuForProfessor(menu_codes);
    }

    private void addSubMenuForProfessor(String[] menu_codes) {
        this.addChapters(menu_codes[0]);
        this.addPreference(menu_codes[1]);
    }

    private void addTopLevelMenu() {
        String[] menu_codes = {"User", "Preferences", "Chapters"};
        String[] menu_levels = {"User Management", "Preferences", "Chapters"};
        int index = 0;
        for (String s : menu_codes) {
            this.menus.add(new ApplicationMenu(s, menu_levels[index], null));
            index++;
        }
        this.addSubMenu(menu_codes);
    }

    private void addSubMenu(String[] menu_codes) {
        this.addUserStructure(menu_codes[0]);
        this.addPreference(menu_codes[1]);
        this.addChapters(menu_codes[2]);

    }

    private void addUserStructure(String parentCode) {

        this.menus.add(new ApplicationMenu("user_manage", "Add User", parentCode, "/faces/users/useradd.xhtml", " ", ApplicationMenu.MenuType.Entries));
        this.menus.add(new ApplicationMenu("user_manage", "Add Course", parentCode, "/faces/courses/courseadd.xhtml", " ", ApplicationMenu.MenuType.Entries));
        this.menus.add(new ApplicationMenu("user_list", "List User", parentCode, "/faces/users/userlist.xhtml", " ", ApplicationMenu.MenuType.Queries));
        this.menus.add(new ApplicationMenu("role_list", "List Course", parentCode, "/faces/courses/courselist.xhtml", " ", ApplicationMenu.MenuType.Queries));

    }

    private void addPreference(String parentCode) {
        this.menus.add(new ApplicationMenu("change_password", "Change Password", parentCode, "/protected/courses/changepassword.xhtml", " ", ApplicationMenu.MenuType.Entries));
        this.menus.add(new ApplicationMenu("email", "Send Email", parentCode, "/faces/email.xhtml", " ", ApplicationMenu.MenuType.Entries));

    }

    private void addChapters(String parentCode) {
        this.menus.add(new ApplicationMenu("chapter_manage", "Add Chapter", parentCode, "/faces/chapters/chapteradd.xhtml", " ", ApplicationMenu.MenuType.Entries));
        this.menus.add(new ApplicationMenu("chapter_list", "List Chapter", parentCode, "/faces/chapters/chapterlist.xhtml", " ", ApplicationMenu.MenuType.Queries));
    }

    private List<ApplicationMenu> findApplicationMenuByTypeAndParenCode(ApplicationMenu.MenuType menuType, String parentCode) {
        List<ApplicationMenu> filteredMenus = new ArrayList<>();
        for (ApplicationMenu menu : this.menus) {
            if (menu.getMenuType() == menuType && StringUtils.equals(menu.getParentCode(), parentCode)) {
                filteredMenus.add(menu);
            }

        }
        return filteredMenus;
    }

    private List<ApplicationMenu> findAllTopLevelApplicationMenu(ApplicationMenu.MenuType menuType) {
        List<ApplicationMenu> filteredMenus = new ArrayList<>();
        for (ApplicationMenu menu : this.menus) {
            if (menu.getMenuType() == menuType) {
                filteredMenus.add(menu);
            }
        }
        return filteredMenus;
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public List<ApplicationMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<ApplicationMenu> menus) {
        this.menus = menus;
    }

    public UserFinder getUserFinder() {
        return userFinder;
    }

    public void setUserFinder(UserFinder userFinder) {
        this.userFinder = userFinder;
    }

}
