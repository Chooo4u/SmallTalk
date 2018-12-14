import pandas as pd
import numpy as np

def table_title():
    # data_title = pd.read_csv('/Users/zoey/WPI/ms571/ms571-project/dataset/title.csv', sep=',')
    data_title = pd.read_csv('/Users/zoey/WPI/ms571/ms571-project/000title.csv', sep=',', encoding = "ISO-8859-1")
    dftitle = pd.DataFrame(data_title)
    # dftitle['year_start'].replace
    # dftitle["year_start"].replace('\\N', 0)
    # cleartitle = dftitle["year_start"]
    # cleartitle["year_start"].astype(int)
    # cleartitle["year_start"].describe()
    # newtitle = cleartitle[cleartitle["year_start"] >= 2000]
    # newtitle.to_csv('title.csv', index=False, sep=',')

def table_episode():
    data_episode = pd.read_csv('/Users/zoey/WPI/ms571/ms571-project/dataset/episode.csv', sep=',')
    data_title = pd.read_csv('/Users/zoey/WPI/ms571/ms571-project/000title.csv', sep=',', encoding = "ISO-8859-1")
    dfeps = pd.DataFrame(data_episode)
    dftitle = pd.DataFrame(data_title)

    year = dftitle[['vid']]
    # print(year)
    ETmerge = pd.merge(dfeps, year, how='inner', on ='vid' )

    ETmerge.to_csv('episode.csv', index=False, sep=',')

def table_profession():
    data_prof = pd.read_csv('/Users/zoey/WPI/ms571/ms571-project/dataset/profession.csv', sep=',')
    data_title = pd.read_csv('/Users/zoey/WPI/ms571/ms571-project/000title.csv', sep=',', encoding = "ISO-8859-1")
    dfprof = pd.DataFrame(data_prof)
    dftitle = pd.DataFrame(data_title)

    title = dftitle[['vid']]
    PTmerge = pd.merge(dfprof, title, how='inner', on ='vid' )

    PTmerge.to_csv('profession.csv', index=False, sep=',')

def table_crew():
    data_prof = pd.read_csv('/Users/zoey/WPI/ms571/ms571-project/profession.csv', sep=',')
    data_crew = pd.read_csv('/Users/zoey/WPI/ms571/ms571-project/dataset/crew0.csv', sep=',')
    dfprof = pd.DataFrame(data_prof)
    dfcrew = pd.DataFrame(data_crew)

    crew = dfprof[['cid']]
    PCmerge = pd.merge(dfcrew, crew, how='inner', on ='cid' )
    f = PCmerge.drop_duplicates(subset=['cid'], keep='first')
    f.to_csv('crew.csv', index=False, sep=',')

# print(table_title())
# print(table_episode())
# print (table_profession())
print (table_crew())