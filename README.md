# MafiaLeaf

<p align="center"><img src="public/image/leaf_logo.png" alt="MafiaLeaf logo" width="200"></p>

**MafiaLeaf** is a Minecraft 1.21.11 server fork of [Leaf](https://github.com/Winds-Studio/Leaf) built only for PigFFA.
Everything that PigFFA does not use (natural mob spawning, locator bar waypoints, upstream update checks) is removed,
and the hot paths of a crowded FFA arena (player tracking, respawns, combat, tick statistics) are optimized.

It stays fully compatible with Bukkit, Spigot, Paper and Leaf plugins.

## Building

```bash
./gradlew applyAllPatches
./gradlew createMojmapPaperclipJar
```

The server jar is written to `mafialeaf-server/build/libs/mafialeaf-paperclip-1.21.11-R0.1-SNAPSHOT-mojmap.jar`.

## Configuration

The global config lives in `config/mafialeaf-global.yml`. An existing `config/leaf-global.yml` is migrated automatically.
Admin command: `/mafialeaf` (alias `/leaf`).

## License

MafiaLeaf keeps the licenses of all upstream projects, see [LICENSE.md](LICENSE.md) and [licenses](licenses).

## 📜 Credits
Thanks to these projects below. MafiaLeaf and Leaf include patches taken from them.<br>
If these excellent projects hadn't existed, neither Leaf nor MafiaLeaf would exist.

- [Gale](https://github.com/Dreeam-qwq/Gale) ([Original Repo](https://github.com/GaleMC/Gale))
- [Pufferfish](https://github.com/pufferfish-gg/Pufferfish)
- [Purpur](https://github.com/PurpurMC/Purpur)
- <details>
    <summary>🍴 Expand to see forks that Leaf takes patches from.</summary>
    <p>
      • <a href="https://github.com/KeYiMC/KeYi">KeYi</a> (R.I.P.)
        <a href="https://github.com/MikuMC/KeYiBackup">(Backup)</a><br>
      • <a href="https://github.com/etil2jz/Mirai">Mirai</a><br>
      • <a href="https://github.com/Bloom-host/Petal">Petal</a><br>
      • <a href="https://github.com/fxmorin/carpet-fixes">Carpet Fixes</a><br>
      • <a href="https://github.com/Akarin-project/Akarin">Akarin</a><br>
      • <a href="https://github.com/Cryptite/Slice">Slice</a><br>
      • <a href="https://github.com/ProjectEdenGG/Parchment">Parchment</a><br>
      • <a href="https://github.com/LeavesMC/Leaves">Leaves</a><br>
      • <a href="https://github.com/KaiijuMC/Kaiiju">Kaiiju</a><br>
      • <a href="https://github.com/PlazmaMC/PlazmaBukkit">Plazma</a><br>
      • <a href="https://github.com/SparklyPower/SparklyPaper">SparklyPaper</a><br>
      • <a href="https://github.com/HaHaWTH/Polpot">Polpot</a><br>
      • <a href="https://github.com/plasmoapp/matter">Matter</a><br>
      • <a href="https://github.com/LuminolMC/Luminol">Luminol</a><br>
      • <a href="https://github.com/Gensokyo-Reimagined/Nitori">Nitori</a><br>
      • <a href="https://github.com/Tuinity/Moonrise">Moonrise</a> (during 1.21.1)<br> 
      • <a href="https://github.com/Samsuik/Sakura">Sakura</a><br> 
    </p>
</details>

## 🔥 Special Thanks

<table>
  <tr>
    <td width="50%" align="center">
      <a href="https://cloud.swordsman.com.cn/?i8ab42c">
        <img src="public/image/JiankeServer.jpg" alt="Jianke Cloud Host" width="250">
      </a>
      <br>
      <b>cloud of swordsman | 剑客云</b>
      <p>If you want to find a cheaper, high performance, stable, lower latency host, then cloud of swordsman is a good choice! Registers and purchases in <a href="https://cloud.swordsman.com.cn/?i8ab42c">here</a>.</p>
      <p>如果你想找一个低价高性能、低延迟的云服务商，剑客云是个不错的选择！你可以在 <a href="https://cloud.swordsman.com.cn/?i8ab42c">这里</a> 注册。</p>
    </td>
    <td width="50%" align="center">
      <a href="https://www.rainyun.com/NzE2NTc1_">
        <img src="public/image/RainYun.jpg" alt="雨云" width="250">
      </a>
      <br>
      <b>RainYun | 雨云</b>
      <p>Global multi-line routing with cloud storage. Refund available within 7 days. Reliable uptime and expert support. RainYun — stable, cost-effective, and ready for fast cloud deployment. Visit <a href="https://www.rainyun.com/NzE2NTc1_">RainYun</a>.</p>
      <p>国际多线路选择，配套云存储 — 购买服务后七天内不满意可以申请退订，强大的技术支持团队和高在线率客服。雨云云服务器，用稳定和性价比，助力您快速上云。点击前往 <a href="https://www.rainyun.com/NzE2NTc1_">雨云</a>。</p>
    </td>
  </tr>
  <tr>
    <td colspan="2" align="center">
      <a href="https://www.yourkit.com/">
        <img src="https://www.yourkit.com/images/yklogo.png" alt="YourKit" width="300">
      </a>
      <p>YourKit supports open source projects with innovative and intelligent tools for monitoring and profiling Java and .NET applications. YourKit is the creator of <a href="https://www.yourkit.com/java/profiler/">YourKit Java Profiler</a>, <a href="https://www.yourkit.com/dotnet-profiler/">YourKit .NET Profiler</a>, and <a href="https://www.yourkit.com/youmonitor/">YourKit YouMonitor</a>.</p>
    </td>
  </tr>
</table>