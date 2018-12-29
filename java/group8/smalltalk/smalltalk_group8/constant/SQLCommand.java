package group8.smalltalk.smalltalk_group8.constant;

/**
 * SQL commands
 * Including select/delete/update/insert
 */
public abstract class SQLCommand
{
    public static String Football_Teams = "select sport.league_abb, team.team_name, team.location, team.wins, team.losses from sport inner join team on sport.sid = team.sid where sport.sport_name = 'football' order by team.team_name";
    public static String Baseball_Teams = " select sport.league_abb, team.team_name, team.location, team.wins, team.losses from sport inner join team on sport.sid = team.sid where sport.sport_name = 'baseball' order by team.team_name";
    public static String Basketball_Teams = " select sport.league_abb, team.team_name, team.location, team.wins, team.losses from sport inner join team on sport.sid = team.sid where sport.sport_name = 'basketball' order by team.team_name";
    public static String Hockey_Teams = " select sport.league_abb, team.team_name, team.location, team.wins, team.losses from sport inner join team on sport.sid = team.sid where sport.sport_name = 'hockey' order by team.team_name";
    public static String Best_Movies_2018 = " select vtitle, year_start, genres, vrating from Title where vrating>9.0 and year_start = '2018' and vtype = 'movie' order by vrating";
    public static String Best_TV_2018 = " select vtitle, year_start, genres, vrating from Title where vrating>9.0 and year_start = '2018' and vtype = 'tvSeries' order by vrating";



}

