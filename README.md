# songLib
songlib app
Authors: Jin Kim

Please add the javafx library in order to run this project.
Make sure the run configuration arguments under VM arguments are consistent with the installation process
as specified by https://openjfx.io/
Example:--module-path "\path\to\javafx-sdk-13\lib" --add-modules javafx.controls,javafx.fxml
(example may vary by OS)

Songlistsave.txt is required to run SongLib as it saves and loads the songs.
It is OK to leave this empty at the start. It is formatted in the following style:

Songname
Artist
Album
Year

where each line represents a new field.
empty fields should be represented with blank lines.
The file however, is not meant to be modified by the user manually.
