package com.github.qq44920040.github.lizizi;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class main extends JavaPlugin {
    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public boolean onCommand(final CommandSender sender, Command command, String label, final String[] args) {
        final Player player = (Player) sender;
        if (args.length==4){
            particleCreate(Bukkit.getPlayer(args[0]).getLocation(),Double.parseDouble(args[1]),Effect.valueOf(args[2]),Boolean.parseBoolean(args[3]));
        }else if (args.length==7){
            if (args[1].equalsIgnoreCase(args[2])){
                return false;
            }

            final BukkitTask a1 = new BukkitRunnable(){
                @Override
                public void run() {
                    Location playerA;
                    Location playerB;
                    if (args[1].contains("name,")){
                        String[] split = args[1].split(",");
                        playerA =new Location(player.getWorld(),Double.parseDouble(split[1]),Double.parseDouble(split[2]),Double.parseDouble(split[3]));
                    }else {
                        playerA = Bukkit.getPlayer(args[1]).getLocation();
                    }
                    if (args[2].contains("name,")){
                        String[] split = args[2].split(",");
                        playerB =new Location(player.getWorld(),Double.parseDouble(split[1]),Double.parseDouble(split[2]),Double.parseDouble(split[3]));
                    }else {
                        playerB =Bukkit.getPlayer(args[2]).getLocation();
                    }
                    if (playerA.getWorld().getName().equalsIgnoreCase(playerB.getWorld().getName())){
                        DoLine(Effect.valueOf(args[0]),playerA.getWorld(),playerA.getX(),playerA.getY()+Double.parseDouble(args[5]),playerA.getZ(),playerB.getX(),playerB.getY()+Double.parseDouble(args[5]),playerB.getZ(),Double.parseDouble(args[6]));
                    }else {
                        cancel();
                    }
                }
            }.runTaskTimer(this,0L,Long.parseLong(args[3]));
            new BukkitRunnable(){
                @Override
                public void run() {
                    a1.cancel();
                }
            }.runTaskLater(this,Long.parseLong(args[4]));
        }
        return super.onCommand(sender, command, label, args);
    }

    private void DoLine(Effect Type,World world, double x1, double y1, double z1, double x2, double y2, double z2, double num){
        double k =1/(Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2+y1,2)+Math.pow(z2+z1,2)));
        double i = 0,x,y,z;
        while (true){
            x=k*num*(x2-x1)*i+x1;
            y=k*num*(y2-y1)*i+y1;
            z=k*num*(z2-z1)*i+z1;
            if (!((x1<=x&&x<=x2)||(x2<=x&&x<=x1))){
                break;
            }
            i+=0.5;
            System.out.println(x+":"+y+":"+z);
            world.playEffect(new Location(world,x,y,z),Type,0);
        }
    }



    public void particleCreate(Location Loc, double Radii, Effect Type,boolean b) {
        if (b){
            double j = 0.0;
            double max = 360.0;
            for (double i = 1.5; i <= Radii; i += 2.0) {
                for (double o = j; o <= max; ++o) {
                    final double x = i * (Math.cos(o / 180.0 * 3.141592653589793) * (-Math.cos((90.0f - Loc.clone().getPitch() + 90.0f) / 180.0f * 3.141592653589793) * Math.sin(Loc.clone().getYaw() / 180.0f * 3.141592653589793))) + i * (Math.sin(o / 180.0 * 3.141592653589793) * -Math.sin((Loc.clone().getYaw() - 90.0f) / 180.0f * 3.141592653589793));
                    final double y = 0.8 + i * (Math.cos(o / 180.0 * 3.141592653589793) * Math.sin((90.0f - Loc.clone().getPitch() + 90.0f) / 180.0f * 3.141592653589793));
                    final double z = i * (Math.cos(o / 180.0 * 3.141592653589793) * (Math.cos((90.0f - Loc.clone().getPitch() + 90.0f) / 180.0f * 3.141592653589793) * Math.cos(Loc.clone().getYaw() / 180.0f * 3.141592653589793)) + Math.sin(o / 180.0 * 3.141592653589793) * Math.cos((Loc.clone().getYaw() - 90.0f) / 180.0f * 3.141592653589793));
                    final Location Location = Loc.clone().add(x, y, z);
                    Loc.clone().getWorld().playEffect(Location, Type, 0);
                }
            }
        }else {
            double j = 90.0;
            double max = 270.0;
            for (double i = 1.5; i <= Radii; i += 2.0) {
                for (double o = j; o <= max; ++o) {
                    final double x = i * (Math.cos(o / 180.0 * 3.141592653589793) * (-Math.cos((90.0f - Loc.clone().getPitch() + 90.0f) / 180.0f * 3.141592653589793) * Math.sin(Loc.clone().getYaw() / 180.0f * 3.141592653589793))) + i * (Math.sin(o / 180.0 * 3.141592653589793) * -Math.sin((Loc.clone().getYaw() - 90.0f) / 180.0f * 3.141592653589793));
                    final double y = 0.8 + i * (Math.cos(o / 180.0 * 3.141592653589793) * Math.sin((90.0f - Loc.clone().getPitch() + 90.0f) / 180.0f * 3.141592653589793));
                    final double z = i * (Math.cos(o / 180.0 * 3.141592653589793) * (Math.cos((90.0f - Loc.clone().getPitch() + 90.0f) / 180.0f * 3.141592653589793) * Math.cos(Loc.clone().getYaw() / 180.0f * 3.141592653589793)) + Math.sin(o / 180.0 * 3.141592653589793) * Math.cos((Loc.clone().getYaw() - 90.0f) / 180.0f * 3.141592653589793));
                    final Location Location = Loc.clone().add(x, y, z);
                    Loc.clone().getWorld().playEffect(Location, Type, 0);
                }
            }
        }
    }
}
