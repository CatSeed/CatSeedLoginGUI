package cc.baka9.catseedlogingui;

import cc.baka9.catseedlogingui.listener.LoginListener;
import cc.baka9.catseedlogingui.listener.RegisterListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.logging.Logger;

/**
 * 插件主类
 */
public class PluginMain extends JavaPlugin {

    public static PluginMain instance;
    public static Logger logger;
    public static BukkitScheduler scheduler;

    @Override
    public void onEnable(){
        instance = this;
        logger = getLogger();
        scheduler = getServer().getScheduler();

        // 注册事件监听器
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new LoginListener(), this);
        pluginManager.registerEvents(new RegisterListener(), this);

    }
}
