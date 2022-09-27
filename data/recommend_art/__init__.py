import py_eureka_client.eureka_client as eureka_client

your_rest_server_port = 8000
# The flowing code will register your server to eureka server and also start to send heartbeat every 30 seconds
eureka_client.init(eureka_server="http://localhost:8761/eureka",
                   app_name="recommend-service",
                   instance_port=your_rest_server_port)