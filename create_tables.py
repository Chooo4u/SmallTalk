import pandas as pd

'''
def table_title():
    data_basics = pd.read_csv('/Users/zoey/WPI/ms571/ms571-project/dataset/title.basics.tsv', sep='\t')
    data_ratings = pd.read_csv('/Users/zoey/WPI/ms571/ms571-project/dataset/title.ratings.tsv', sep='\t')
    df_basics = pd.DataFrame(data_basics)
    df_ratings = pd.DataFrame(data_ratings)

    Twant_inB = df_basics[['tconst', 'titleType', 'primaryTitle', 'startYear', 'endYear','genres']]
    Twant_inR = df_ratings[['tconst','averageRating']]
    table_t = pd.merge(Twant_inB, Twant_inR)
    title = table_t.rename(columns={'tconst':'vid', 'titleType':'vtype', 'primaryTitle':'vtitle', 'startYear':'year_start', 'endYear':'year_end','averageRating':'vrating'})
    title.to_csv('/Users/zoey/WPI/ms571/ms571-project/dataset/title.csv', index=False, sep=',')
'''
def table_episode():
    data_basics = pd.read_csv('/Users/zoey/WPI/ms571/ms571-project/dataset/title.basics.tsv', sep='\t')
    data_ratings = pd.read_csv('/Users/zoey/WPI/ms571/ms571-project/dataset/title.ratings.tsv', sep='\t')
    df_basics = pd.DataFrame(data_basics)
    df_ratings = pd.DataFrame(data_ratings)

    data_episode = pd.read_csv('/Users/zoey/WPI/ms571/ms571-project/dataset/title.episode.tsv', sep='\t')
    df_episode = pd.DataFrame(data_episode)

    Ewant_inB = df_basics[['tconst', 'primaryTitle']]
    Ewant_inB2 = df_basics[['tconst', 'primaryTitle']]
    Ewant_inBB = Ewant_inB2.rename(columns={'tconst':'parentTconst', 'primaryTitle':'vtitle'})
    Ewant_inR = df_ratings[['tconst','averageRating']]
    Ewant_inE = df_episode[['tconst','parentTconst','seasonNumber','episodeNumber']]
    E_mergeRE = pd.merge(Ewant_inE, Ewant_inR)
    E_mergeREB = pd.merge(E_mergeRE, Ewant_inB)
    table_E = pd.merge(E_mergeREB, Ewant_inBB)
    episode = table_E.rename(columns={'tconst':'eid', 'parentTconst':'vid', 'primaryTitle':'etitle','averageRating':'rating','seasonNumber':'num_season','episodeNumber':'num_episode' })
    episode.to_csv('/Users/zoey/WPI/ms571/ms571-project/dataset/episode.csv', index=False, sep=',')

def table_crew():
    data_name = pd.read_csv('/Users/zoey/WPI/ms571/ms571-project/dataset/name.basics.tsv', sep='\t')
    df_name = pd.DataFrame(data_name)
    Cwant_inN = df_name[['nconst','primaryName','birthYear','deathYear']]

    Cname = Cwant_inN['primaryName'].str.split(' ', expand = True)
    CnameMat = Cname.as_matrix()
    for i in range(len(CnameMat)):
        j = 2
        if CnameMat[i,j]!= None:
            if CnameMat[i,j+1]!= None:
                j = j+1
            else:
                CnameMat[i,1] = CnameMat[i, j]
    newname = pd.DataFrame(Cname)
    pd_name = newname[[0,1]]
    pd_name.columns = ['first_name','last_name']

    age_exist = df_name[['nconst','birthYear','deathYear']]
    # age_exist = age_exist.rename(columns={'nconst':'cid','birthYear':'age','deathYear':'if_exist'})
    age_existMat = age_exist.as_matrix()
    for i in range(len(age_existMat)):
        if age_existMat[i, 1] == '\\N':
            age_existMat[i, 1] = 'Missing'
            age_existMat[i, 2] = 'Missing'
        else:
            if age_existMat[i, 2] == '\\N':
                age_existMat[i, 2] = 'N'
                age_existMat[i, 1] = 2018 - int(age_existMat[i, 1])
            else:
                age_existMat[i, 1] = int(age_existMat[i, 2]) - int(age_existMat[i, 1])
                age_existMat[i, 2] = 'Y'

    pd_age_exist = pd.DataFrame(age_existMat)
    pd_age_exist.columns = ['cid','age','if_exist']

    Cname_merge = pd.concat([pd_age_exist,pd_name],axis=1,ignore_index=True)
    Cname_merge.columns = ['cid', 'age', 'if_exist','first_name','last_name']

    Cname_merge.to_csv('/Users/zoey/WPI/ms571/ms571-project/dataset/crew0.csv', index=False, sep=',')



def table_profession():
    data_principal = pd.read_csv('/Users/zoey/WPI/ms571/ms571-project/dataset/title.principals.tsv', sep='\t')
    df_prin = pd.DataFrame(data_principal)
    Cwant_inP = df_prin[['tconst','nconst','category','characters']]
    Cwant_inP.columns = ['vid','cid','profession','character']
    # table_C = pd.concat(Cname_merge,Cwant_inP, join='outer',ignore_index=True)
    Cwant_inP.to_csv('/Users/zoey/WPI/ms571/ms571-project/dataset/profession.csv', index=False, sep=',')

print(table_title())
print(table_episode())
print(table_crew())
print(table_profession())