package cc.baka9.catseedlogingui;

import lk.vexview.api.VexViewAPI;
import lk.vexview.builders.ButtonBuilder;
import lk.vexview.builders.GuiBuilder;
import lk.vexview.builders.InputFieldBuilder;
import lk.vexview.gui.VexGui;
import lk.vexview.gui.components.VexButton;
import lk.vexview.gui.components.VexHoverText;
import lk.vexview.gui.components.VexTextField;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collections;

/**
 * 创建界面工厂
 */
public class GuiFactory {

    /**
     * 创建一个登录界面的 VexGui 对象
     */
    public static VexGui createLoginGui(Player player){

        VexGui gui = GuiBuilder.builder()
                .size(120, 100)
                .text(text -> text.addLine("请输入密码:").offset(30, 15))
                .closable(false)
                .build("[local]CatSeedLogin/panel.png", -1, -1);

        VexTextField textPassword = (VexTextField) InputFieldBuilder.builder()
                .id("CatSeedLoginPasswordText".hashCode())
                .size(60, 10)
                .offset(31, 30)
                .hover(new VexHoverText(Collections.singletonList("在此输入密码")))
                .build();

        VexButton btnLogin = ButtonBuilder.builder()
                .text("").id("CatSeedLoginLoginBtn")
                .size(30, 15)
                .offset(30, 45)
                .background("[local]CatSeedLogin/buttonLogin.png",
                        "[local]CatSeedLogin/buttonLoginFocus.png")
                .click(p -> {
                    String typedText = textPassword.getTypedText();
                    Bukkit.dispatchCommand(p, String.format("login %s", typedText));
                })
                .build();

        VexButton btnForget = ButtonBuilder.builder()
                .text("").id("CatSeedLoginForgetBtn")
                .size(30, 15)
                .offset(30 + 30 + 2, 45)
                .background("[local]CatSeedLogin/buttonForget.png",
                        "[local]CatSeedLogin/buttonForgetFocus.png")
                .click(p -> VexViewAPI.openGui(player, createResetPasswordGui(player)))
                .build();

        VexButton btnQuit = ButtonBuilder.builder()
                .text("").id("CatSeedLoginQuitBtn")
                .size(30, 15)
                .offset(30, 45 + 15 + 2)
                .background("[local]CatSeedLogin/buttonQuit.png",
                        "[local]CatSeedLogin/buttonQuitFocus.png")
                .click(p -> p.kickPlayer("退出服务器"))
                .build();

        gui.addComponent(textPassword);
        gui.addComponent(btnLogin);
        gui.addComponent(btnQuit);
        gui.addComponent(btnForget);
        return gui;
    }


    /**
     * 创建一个注册界面的 VexGui 对象
     */
    public static VexGui createRegisterGui(){
        VexGui gui = GuiBuilder.builder()
                .size(120, 100)
                .text(text -> text.addLine("输入两次要注册的密码:").offset(15, 13))
                .closable(false)
                .build("[local]CatSeedLogin/panel.png", -1, -1);

        int currentOffsetY = 25;
        VexTextField textRegisterPassword = (VexTextField) InputFieldBuilder.builder()
                .id("CatSeedLoginRegisterPasswordText".hashCode())
                .size(60, 10)
                .offset(31, currentOffsetY)
                .hover(new VexHoverText(Collections.singletonList("在此输入要注册的密码")))
                .build();

        currentOffsetY = currentOffsetY + 10 + 5;
        VexTextField textConfirmPassword = (VexTextField) InputFieldBuilder.builder()
                .id("CatSeedLoginConfirmPasswordText".hashCode())
                .size(60, 10)
                .offset(31, currentOffsetY)
                .hover(new VexHoverText(Collections.singletonList("再次输入确认要注册的密码")))
                .build();

        currentOffsetY = currentOffsetY + 10 + 5;
        VexButton btnRegister = ButtonBuilder.builder()
                .text("").id("CatSeedLoginRegisterBtn")
                .size(30, 15)
                .offset(30, currentOffsetY)
                .background("[local]CatSeedLogin/buttonRegister.png",
                        "[local]CatSeedLogin/buttonRegisterFocus.png")
                .click(p -> {
                    String registerPassword = textRegisterPassword.getTypedText();
                    String confirmPassword = textConfirmPassword.getTypedText();
                    Bukkit.dispatchCommand(p, String.format("register %s %s", registerPassword, confirmPassword));
                })
                .build();

        currentOffsetY = currentOffsetY + 15 + 2;
        VexButton btnQuit = ButtonBuilder.builder()
                .text("").id("CatSeedLoginQuitBtn")
                .size(30, 15)
                .offset(30, currentOffsetY)
                .background("[local]CatSeedLogin/buttonQuit.png",
                        "[local]CatSeedLogin/buttonQuitFocus.png")
                .click(p -> p.kickPlayer("退出服务器"))
                .build();

        gui.addComponent(textRegisterPassword);
        gui.addComponent(textConfirmPassword);
        gui.addComponent(btnRegister);
        gui.addComponent(btnQuit);
        return gui;
    }

    /**
     * 创建一个邮箱验证码重置密码的界面
     */
    public static VexGui createResetPasswordGui(Player player){

        VexGui gui = GuiBuilder.builder()
                .size(120, 100)
                .text(text -> text.addLine("输入邮箱验证码和新密码:").offset(10, 13))
                .closable(false)
                .build("[local]CatSeedLogin/panel.png", -1, -1);

        int currentOffsetY = 25;
        VexTextField textNewPassword = (VexTextField) InputFieldBuilder.builder()
                .id("CatSeedLoginNewPasswordText".hashCode())
                .size(60, 10)
                .offset(31, currentOffsetY)
                .hover(new VexHoverText(Collections.singletonList("在此输入新密码")))
                .build();

        currentOffsetY = currentOffsetY + 10 + 5;
        VexTextField textMailCode = (VexTextField) InputFieldBuilder.builder()
                .id("CatSeedLoginMailCodeText".hashCode())
                .size(30, 10)
                .offset(31, currentOffsetY)
                .hover(new VexHoverText(Collections.singletonList("在此输入邮箱验证码")))
                .build();

        VexButton btnSendMailCode = ButtonBuilder.builder()
                .text("").id("CatSeedLoginSendMailCodeBtn")
                .size(30, 15)
                .offset(30 + 30 + 3, currentOffsetY - 2)
                .background("[local]CatSeedLogin/buttonSendMailCode.png",
                        "[local]CatSeedLogin/buttonSendMailCodeFocus.png")
                .click(p -> Bukkit.dispatchCommand(p, "resetpassword forget"))
                .build();

        currentOffsetY = currentOffsetY + 10 + 5;
        VexButton btnResetPassword = ButtonBuilder.builder()
                .text("").id("CatSeedLoginResetPasswordBtn")
                .size(30, 15)
                .offset(30, currentOffsetY)
                .background("[local]CatSeedLogin/buttonResetPassword.png",
                        "[local]CatSeedLogin/buttonResetPasswordFocus.png")
                .click(p -> {
                    String newPassword = textNewPassword.getTypedText();
                    String mailCode = textMailCode.getTypedText();
                    Bukkit.dispatchCommand(p, String.format("resetpassword re %s %s", mailCode, newPassword));
                })
                .build();

        currentOffsetY = currentOffsetY + 15 + 2;
        VexButton btnBackLogin = ButtonBuilder.builder()
                .text("").id("CatSeedLoginBackLoginBtn")
                .size(30, 15)
                .offset(30, currentOffsetY)
                .background("[local]CatSeedLogin/buttonBackLogin.png",
                        "[local]CatSeedLogin/buttonBackLoginFocus.png")
                .click(p -> VexViewAPI.openGui(player, createLoginGui(player)))
                .build();

        gui.addComponent(textNewPassword);
        gui.addComponent(textMailCode);
        gui.addComponent(btnResetPassword);
        gui.addComponent(btnSendMailCode);
        gui.addComponent(btnBackLogin);
        return gui;
    }


}
