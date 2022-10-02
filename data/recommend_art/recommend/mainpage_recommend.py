from ..models import ChangedPainting, Painting, DetailRecommendedPainting, MemberLog
import numpy as np
import pandas as pd
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity
from django_pandas.io import read_frame
from datetime import datetime, time, timedelta, timezone




def make_isoformat(time_hour:str):
    my_time = datetime(int(time_hour[:4]),int(time_hour[5:7]),int(time_hour[8:10]),int(time_hour[11:13]),int(time_hour[14:16]),int(time_hour[17:19]),int(time_hour[20:23]))
    my_time = my_time + timedelta(hours=9)
    return my_time

def make_hour(time_hour:str):
    my_hour = int(time_hour[11:13]) + 9
    return my_hour

def make_timezone(my_hour):
    if my_hour >= 0 and my_hour < 6:
        result = '새벽'
    elif my_hour >= 6 and my_hour < 12:
        result = '아침'
    elif my_hour >= 12 and my_hour < 18:
        result = '오후'
    else: 
        result = '저녁'
    return result

def make_second_time(my_time):
    return my_time.seconds

def change_painting_id(painting_object):
    end_idx = 0
    start_idx = 0
    for i in range(len(painting_object))[::-1]:
        if painting_object[i] == ')':
            end_idx = i
        elif painting_object[i] =='(':
            start_idx = i+1
    # print(type(painting_object))
    return int(painting_object[start_idx:end_idx])
    

def make_mainpage_recommend(member_id: int):
    today = datetime.now().hour
    today = make_timezone(today)
    member_log = MemberLog.objects.all()
    log_df = read_frame(member_log)
    log_df = log_df[log_df['member_id']==member_id]
    if len(log_df) == 0:
        return 0
    log_df['in_hour'] = log_df['in_time'].map(make_hour)
    log_df['user_timezone'] = ''
    log_df['user_timezone'] = log_df['in_hour'].map(make_timezone)
    log_timezone = log_df.groupby('user_timezone').size()
    try:
        if log_timezone.loc[today] > 20:
            log_df = log_df[log_df['user_timezone']==today]
    except:
        pass
    log_df['in_time'] = log_df['in_time'].map(make_isoformat)
    log_df['out_time'] = log_df['out_time'].map(make_isoformat)
    log_df['invite_time'] = log_df['out_time'] - log_df['in_time']
    log_df['invite_time'] = log_df['invite_time'].map(make_second_time)
    log_df['average_zoom'] = log_df['invite_time'] / log_df['zoom_count']
    
    log_df.loc[log_df['average_zoom']>2100, 'average_zoom'] = 2100
    log_df['invite_time'] = log_df['average_zoom'] * log_df['zoom_count']

    log_df = log_df.drop(['log_id','in_time', 'out_time','in_hour','member_id', 'average_zoom'], axis=1)
    log_df = log_df.groupby('painting_id').sum().reset_index()
    # 변환횟수
    change_painting = ChangedPainting.objects.all()
    if change_painting:
        change_df = read_frame(change_painting)
        change_df = change_df[change_df['member_id'] == member_id]
        change_df['painting_id'] = change_df['painting'].map(change_painting_id)
        change_df = change_df.groupby('painting_id').size().reset_index()
        log_df = pd.merge(log_df, change_df, how='outer', on='painting_id')
        log_df.rename(columns = {0:'change_cnt'},inplace=True)
        log_df['average_change_time'] = log_df['change_cnt'] / log_df['click_count']
        log_df['average_invite_time'] = log_df['invite_time'] / log_df['click_count']
        log_df['average_zoom_count'] = log_df['zoom_count'] / log_df['click_count']
        log_df = log_df.sort_values(['click_count','average_change_time', 'average_zoom_count','average_invite_time'],ascending=False)
    else:
        log_df['average_invite_time'] = log_df['invite_time'] / log_df['click_count']
        log_df['average_zoom_count'] = log_df['zoom_count'] / log_df['click_count']
        log_df = log_df.sort_values(['click_count', 'average_zoom_count','average_invite_time'],ascending=False)
    log_df = log_df.fillna(0)
    # print(log_df)
    
    return log_df