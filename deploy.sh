rm -rf build/ runtime/
ant
cd runtime/local
zip -r nutch.zip *
cd -
sls deploy --aws-profile personal
