FROM nginx
COPY conf/default.conf /etc/nginx/conf.d/default.conf
COPY dist/angular /usr/share/nginx/html