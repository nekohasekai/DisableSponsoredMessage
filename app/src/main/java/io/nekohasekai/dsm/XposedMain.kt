package io.nekohasekai.dsm

import android.util.Log
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class XposedMain : IXposedHookLoadPackage {

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (lpparam.packageName != "org.thunderdog.challegram") {
            return
        }
        runCatching {
            XposedHelpers.findAndHookMethod(
                Class.forName(
                    "org.drinkless.td.libcore.telegram.TdApi\$GetChatSponsoredMessage",
                    false,
                    lpparam.classLoader
                ),
                "getConstructor",
                object : XC_MethodReplacement() {
                    override fun replaceHookedMethod(param: MethodHookParam?): Any {
                        // getMe
                        return -191516033
                    }
                }
            )
        }.onFailure {
            Log.e("DisableSponsoredMessage", "hook failed", it)
        }
    }
}