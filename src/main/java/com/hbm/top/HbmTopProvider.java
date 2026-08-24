package com.hbm.top;

/**
 * TOP 集成桩（P9）。
 * 
 * 注：The One Probe 为可选依赖，正式集成时需：
 * 1. 在 build.gradle 中添加 compileOnly('mcp.mobius.wip:TheOneProbe:${top_version}')
 * 2. 取消注释下方代码并实现具体信息提供
 */
// public class HbmTopProvider {
// 
//     public static void init() {
//         // TODO P9: 实现 TOP 初始化
//     }
// 
//     public static boolean isTopAvailable() {
//         try {
//             Class.forName("mcjty.theoneprobe.api.ITheOneProbe");
//             return true;
//         } catch (ClassNotFoundException e) {
//             return false;
//         }
//     }
// }
