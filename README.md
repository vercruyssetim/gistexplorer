# Gist Explorer

You can use this application to check the progress on the forked gists your gist.

The file in the gist should have the following in order for the application to calculate progress:
 - "[✔] Some text" for completed tasks.
 - "[❌] Some text" or "[] Some text" for not completed tasks.
 
## Running the application

If you just pulled this application from git, run the docker command to build an image:

```docker build . -t <tagname>```

After building the image run the following command to start the application:

``docker run -p 8080:8080 <tagname> -e GISTID=<id_of_gist>`` 

\<tagname> is the name you want to give to your image.

\<id_of_gist> can be found at the end of the url of the creator of gist.

When the application has started go to http://localhost:8080/getProgress. Loading can take a while because
the application needs to clone every forked gist. The result will be an overview of the completion statuses for every 
user that has forked your gist.
