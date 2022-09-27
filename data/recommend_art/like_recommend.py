from .models import LikedPainting, Painting
import numpy as np
import pandas as pd
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity
from django_pandas.io import read_frame


def find_sim_painting_item(df, title_name, top=20):
    like_rmd_lst = []
    
    try:
        title_painting_sim =df[[title_name]].drop(title_name, axis=0)
        title_painting_sim = title_painting_sim.sort_values(title_name, ascending=False)[:top_n]
    except:
        return like_rmd_lst
    for i in range(len(title_painting_sim)):
        if title_painting_sim[title_name][i] > 0.5:
            like_rmd_lst.append(title_painting_sim.index[i])
        else:
            break
    return like_rmd_lst
    


def recommend_like_painting():
    paintings = Painting.objects.all()
    painting_df = read_frame(paintings)
    like_painting = LikedPainting.objects.all()
    like_df = read_frame(like_painting)
    like_df['like'] = 1
    
    like_painting_df = pd.merge(like_df, painting_df, on="painting_id")
    
    like_painting_matrix = like_painting_df.pivot_table('like', index='member_id', columns="title")
    like_painting_matrix.fillna(0, inplace=True)
    like_painting_matrix_T = like_painting_matrix.transpose()
    
    item_sim = cosine_similarity(like_painting_matrix_T, like_painting_matrix_T)
    item_sim_df = pd.DataFrame(data=item_sim, index=like_painting_matrix.columns, columns=like_painting_matrix.columns)
    
    return item_sim_df