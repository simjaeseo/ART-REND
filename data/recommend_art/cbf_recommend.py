from .models import Painting
import numpy as np
import pandas as pd
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity
from django_pandas.io import read_frame

def make_recommend_art(df, art_title, weight_cosine_sim, top=20):
    target_art_idx = df[df['그림 제목'] == art_title].index.values
    sim_index = weight_cosine_sim[target_art_idx, : top].reshape(-1)
    sim_index = sim_index[sim_index != target_art_idx]

    result = df.iloc[sim_index][:top]
    result = result[['index','그림 제목']]
    return result

def art_recommend():
    paintings = Painting.objects.all()
    artist_df = read_frame(paintings)
    
    artist_df = artist_df.replace('인상주의, 점묘법', '인상주의 점묘법')
    artist_df = artist_df.replace('초현실주의, 상징주의', '초현실주의 상징주의')
    
    # genre_lst = set():
    #     for genre
    artist_df['weight'] = artist_df['예술사조'] + ' ' + artist_df['소재']
    artist_df = artist_df.reset_index()
    
    count_vector = CountVectorizer(ngram_range=(1,3))
    
    weight_cosine_vector = count_vector.fit_transform(artist_df['weight'])
    
    weight_cosine_sim = cosine_similarity(weight_cosine_vector, weight_cosine_vector).argsort()[:,::-1]
    
    
    