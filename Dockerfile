FROM nginx
COPY conf/default.conf /etc/nginx/conf.d/default.conf
COPY dist/my-app /usr/share/nginx/html