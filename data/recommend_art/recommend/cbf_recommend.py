from ..models import Painting, DetailRecommendedPainting
import numpy as np
import pandas as pd
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity
from django_pandas.io import read_frame

def make_recommend_art(df, artist, art_title, weight_cosine_sim, top=20):
    if art_title == "Self-Portrait":
        print(art_title)
    target_art_idx = df[(df['title'] == art_title) & (df['artist'] == artist)].index.values
    sim_index = weight_cosine_sim[target_art_idx, : top].reshape(-1)
    sim_index = sim_index[sim_index != target_art_idx]

    result = df.iloc[sim_index][:top]
    result = result[['paintingId','title']]
    if art_title == "Self-Portrait":
        print(sim_index)
        print(result)
    return result

def art_recommend(top):
    paintings = Painting.objects.all()
    artist_df = read_frame(paintings)
    
    
    artist_df = artist_df.replace('후기 인상주의', '후기인상주의')
    artist_df = artist_df.replace('인상주의, 점묘법', '인상주의 점묘법')
    artist_df = artist_df.replace('초현실주의, 상징주의', '초현실주의 상징주의')
    
    # genre_lst = set():
    #     for genre
    artist_df['weight'] = artist_df['artTrend'] + ' ' + artist_df['genre']
    artist_df = artist_df.reset_index()
    
    count_vector = CountVectorizer(ngram_range=(1,3))
    
    weight_cosine_vector = count_vector.fit_transform(artist_df['weight'])
    
    weight_cosine_sim = cosine_similarity(weight_cosine_vector, weight_cosine_vector).argsort()[:,::-1]
    
    idx = 1
    for painting in paintings:
        title = painting.title
        artist = painting.artist
        try:
            # if title == "Self-Portrait":
                # print(title)
                # print(artist_df.loc[60])
                # print(weight_cosine_sim)
            recommend_art = make_recommend_art(artist_df, artist, title, weight_cosine_sim, top)
            if title == "Self-Portrait":
                print(recommend_art)
            for art_id in recommend_art['paintingId']:
                a = Painting.objects.get(paintingId=art_id)
                paint = painting

                detail = DetailRecommendedPainting(detail_recommended_painting_id= idx)
                idx += 1

                detail.detail_painting = paint

                detail.recommended_painting_id = a
                detail.save()

        except ValueError:
            continue
        