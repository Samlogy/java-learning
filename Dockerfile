FROM nginx
COPY conf/default.conf /etc/nginx/conf.d/default.conf
COPY dist/front-angular /usr/share/nginx/html
# Expose 5001