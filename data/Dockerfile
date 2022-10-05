FROM python:3.9.5
WORKDIR /var/jenkins_home/workspace/deploy/data
COPY requirements.txt ./

RUN pip install opencv-python
RUN pip install django-sslserver
RUN apt-get update
RUN apt-get -y install libgl1-mesa-glx
RUN apt-get -y install libglib2.0-0
RUN pip install --upgrade pip
RUN pip install -r requirements.txt
COPY . .
CMD ["python", "manage.py", "runsslserver", "0.0.0.0:8000"]