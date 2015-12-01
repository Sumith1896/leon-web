PORT=`cat conf/setup.conf | grep http.port | cut -d= -f2`
if [ "$PORT" == "" ]; then
    PORT=9000
fi

#Generate documentation
(cd leon; sbt make-site)

./gen_version.sh

while [ /bin/true ]; do
    activator "start $PORT"
    echo "Shutdown finished!"
    echo "Restarting in 5 seconds..."
    sleep 5
    rm -f target/universal/stage/RUNNING_PID
done
