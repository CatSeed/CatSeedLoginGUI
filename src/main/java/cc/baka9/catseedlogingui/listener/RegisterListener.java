package cc.baka9.catseedlogingui.listener;

import cc.baka9.catseedlogin.CatSeedLoginAPI;
import cc.baka9.catseedlogin.event.CatSeedPlayerLoginEvent;
import cc.baka9.catseedlogin.event.CatSeedPlayerRegisterEvent;
import cc.baka9.catseedlogingui.GuiFactory;
import lk.vexview.api.VexViewAPI;
import lk.vexview.event.VerificationFinishEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * 注册相关的监听器
 */
public class RegisterListener implements Listener {
    /**
     * 实现进服给玩家打开一个Gui界面，需要此事件中打开，而不是PlayerJoinEvent事件
     * 玩家进入服务器在未注册的情况下打开注册界面
     */
    @EventHandler
    public void onVerificationFinish(VerificationFinishEvent event){
        Player player = event.getPlayer();
        if (!CatSeedLoginAPI.isRegister(player.getName())) {
            VexViewAPI.openGui(player, GuiFactory.createRegisterGui());
        }
    }

    /**
     * 当接收到CatSeedLogin的注册事件，玩家注册成功关闭注册界面
     */
    @EventHandler
    public void onPlayerLogin(CatSeedPlayerRegisterEvent event){
        Player player = event.getPlayer();
        player.closeInventory();
    }


}
