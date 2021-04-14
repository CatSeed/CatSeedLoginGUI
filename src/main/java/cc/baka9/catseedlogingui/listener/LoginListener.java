package cc.baka9.catseedlogingui.listener;

import cc.baka9.catseedlogin.CatSeedLoginAPI;
import cc.baka9.catseedlogin.event.CatSeedPlayerLoginEvent;
import cc.baka9.catseedlogingui.GuiFactory;
import lk.vexview.api.VexViewAPI;
import lk.vexview.event.VerificationFinishEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * 登录相关的监听器
 */
public class LoginListener implements Listener {

    /**
     * 实现进服给玩家打开一个Gui界面，需要此事件中打开，而不是PlayerJoinEvent事件
     * 玩家进入服务器在已经注册的情况下打开登录界面
     */
    @EventHandler
    public void onVerificationFinish(VerificationFinishEvent event){
        Player player = event.getPlayer();
        if (CatSeedLoginAPI.isRegister(player.getName())) {
            VexViewAPI.openGui(player, GuiFactory.createLoginGui(player));
        }
    }

    /**
     * 当接收到CatSeedLogin的登录事件，玩家登录成功关闭登录界面，登录失败重新打开登录界面
     */
    @EventHandler
    public void onPlayerLogin(CatSeedPlayerLoginEvent event){
        Player player = event.getPlayer();
        CatSeedPlayerLoginEvent.Result result = event.getResult();
        if (result == CatSeedPlayerLoginEvent.Result.SUCCESS) {
            player.closeInventory();
        } else if (result == CatSeedPlayerLoginEvent.Result.FAIL) {
            VexViewAPI.openGui(player, GuiFactory.createLoginGui(player));
        }
    }


}
