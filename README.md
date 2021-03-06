DFLIConv2
=========
This is my second attempt to write a generic image converter targeting the Commodore Plus/4.

## Design goals
I want it to be easily extendible. For this reason I want a converter that doesn't require a specific optimization algorithm 
for each supported graphic mode. All of these optimization methods are very similar anyway, so I just want to define the problem 
at hand and a few well written algorithms should do the job. I also want it to be highly customizable. I want to specify the 
width and height of the target (multi-screen graphic) and I also want to specify simple constraints if needed, like a fixed 
background color or the same (but optimized) xshift value for the whole screen instead of independently optimized values for
each raster line. The complete separation of the optimization problem and the optimization algorithm would even allow the user
to define new graphic modes without coding. He/she would only need to write some configuration files that define the optimization 
variables (typically colors and pixels) and how those variables define the picture and the output file. Actually, every image 
converter has these definitions, I just want to make them explicit and enable the user to define them.

## Design non-goals

I don't want it to be super fast, only reasonably fast, I don't care if it takes a minute to convert an image.
I don't want to deal with resizing the image to the dimensions of the given graphic mode. It is your job to prepare your input.

## How does the conversion work?

All conversion begins by creating baseline image of the given graphic mode. The baseline is either created randomly or a previous
conversion result is imported by the user (see -b and -bf options).
Than multipe optimization algorithms are run repeatedly:

 * Coordinate pre-optimization is used to fine tune coordinate shifts (like xshift) to reduce the average number of distinct colors per cell (like character) that exceed the capacity of the given graphic mode.
 * One dimensional brute-force algorithm that tries every possible value of every variable, evaluates the conversion error and keeps the values resulting less error.
 * Two-three dimensional brute-force algorithm that tries every possible variation of certain type of variables if the overall number of variations does not exceed 256.
 * K-Means like optimization that tries to find partitions of the pixels whose means are close to possible palette colors.

Those are combined/iterated until the conversion error cannot be reduced further.
If the baseline is randomly generated almost every conversion will yield slightly different results. Use the `-seed <integer>` option to make it deterministic.

## Examples 

### Basic help on options

`> dfliconv2 -h`

### Simple conversion examples

Convert to multi-dfli with preview .png without dithering:

`> dfliconv2 image.jpg`

To hires, saving `image.prg` and `image_preview.png` (the input without file extension is the output file prefix by default):

`> dfliconv2 -m hires -i image.jpg -p`

To multi, using Bayer4x4 dithering, saving `converted.prg` and `converted_preview.png`:

`> dfliconv2 -m multi -d bayer4x4 -i image.jpg -o converted -p`

To generic multi fli mode with badline config 'clllllll':

`> dfliconv2 -m gfli('m','clllllll') -d point5 -i image.jpg -p`

To generic multi fli mode with zoom4 dma pattern 'c--lc--l':

`> dfliconv2 -m gfli('m','c--lc--l') -d point5 -i image.jpg -p`

### Custom palettes

You can use a 16x8 color table in a PNG image as a Plus/4 palette with the -pal option. 
There are some examples ain the pal folder, two of them were directly downloaded from the https://www.colodore.com website.

### Create a dithered image without conversion

You can simply convert an image to Plus/4 colors with dithering as well.

Create a hires dithered image with Plus/4 colors.

`> dfliconv2 -i image.jpg -d point5`

Create a multi dithered image with Plus/4 colors.

`> dfliconv2 -i image.jpg -d bayer2x2 -2x`


### Conversion between formats

It is possible to load a previous conversion results or images in supported formats as a baseline and save them in another format and/or create a .png preview for them.
Let's suppose you have `pic_boti.prg` in Multi Botticelli format and you want to create a .prg viewer for it:

`> dfliconv2 -m multi -f prg -b pic -bf boti`

or you can create only a `pic_preview.png` too:

`> dfliconv2 -m multi -f none -b pic -bf boti -p`

### Supported graphic modes

Get the list of supported modes by running

`> dfliconv2 -m ?`

The current list is:
 * hires, hires(w,h)
 * hires+, hires+(w,h)
 * hires-dfli, hires-dfli(w,h)
 * multi, multi(w,h)
 * multi+, multi+(w,h)
 * multi-dfli, multi-dfli(w,h)
 * gfli(m,dma0,dma1,dma2,dma3,dma4,dma5,dma6,dma7), gfli(w,h,m,dma0,dma1,dma2,dma3,dma4,dma5,dma6,dma7)

The list includes parameterized modes too, like `hires(w,h)` where `w` and `h` stands for width and height in characters.
So, for example `-m "hires(80,25)"` means hires bitmap of dimensions 640x200, ie. a double screen hires mode.
For generic fli (gfli) `m` is one of 'm' or 'h' what stands for multi or hires and `dma0...dma7` each can be one of 'c','l' or '-' what stands for chroma dma, luma dma or no dma. Good to know that chroma dma shows the new colors in the same rasterline but luma dma only has its effect on the next rasterline. The normal dma sequence is 'c------l', the DFLI dma sequence is 'clclclcl' and the zoom4 dma sequence is 'c--lc--l'.

### Supported output formats

Try running a command like below to get the supported output formats for a given mode.

`> dfliconv2 -m hires -f ?`

The `bin` format is supported for every native modes, that will save separate files for luma, chroma, bitmap, etc.
Not all supported modes have a executable `prg` format yet, like `prg` is not supported yet for any of the custom size modes.
`boti` is supported for default size `hires` and `multi` modes. 
`p4fli` is supported for DFLI modes, this utilizes Plus4Emu's converter viewer as it is.

### Supported dithering methods

`> dfliconv2 -d ?`

 * `fs` stands for Floyd-Steinberg dithering.
 * `point5` only uses plain colors or chessboard dithering. 
 * `point5`, `bayer2x2`, `bayer4x4`, `ord3x3`, `ord2x4` only use "close" colors to reduce noise (see below). 

Other dithering methods may be added later.

All dithering methods except fs only use relatively "close" colors for dithering to reduce the noise in the result. The definition of "close" is the distance between the palette colors 0x00 and 0x11. You can use the -cc 0x??-0x?? option to override that, the default is equivalent to -cc 0x00-0x11.

### List of graphic mode variables

You can print a summary of the variables that are optimized when converting to the given mode, like this:

`> dfliconv2 -m multi+`

You can see that in this mode you have separate color0, color3 and xshift variables for every raster-line.

### Variable restrictions

Let's suppose you rather want one xshift value for all lines, but you want the converter to optimize that
and you want color0 to be black in every line. You can do it like this:

`> dfliconv2 -m multi+ -r "xshift_000...xshift_199=xshift_000" -r "color0_000...color0_199=0"`

This will replace the variables xshift_000 to xshift_199 with a single variable xshift_000 and it will also set 
a fixed 0 value for all the color0 variables.

Let's say you want a hires mode where chars are leaning right 45 degrees. That is

`> dfliconv2 -m hires+ -r "xshift_000...xshift_199=7,6,5,4,3,2,1,0"`

On the left side of `=` you can have a variable or a range of variables like above and on the right side you can specify a 
single or comma separated list of variables and integer constants. If the variable list on the left side is longer than the list 
on the right side the later is repeated periodically during assignment. 

You don't have to use a "full" range, you can use "xshint_008...xshift_015" too. Every variable that is lexicographically between the beginning and the end of the range will be included in the range in lexicographical order.

I think you get the idea.

One more thing: You can use -1 on the right hand side for colors. That will instruct the converter to ignore that color on the picture. You might want to use that color in your sprite over the bitmap, right? ;)

### Other notes

The input image is never resized (as mentioned above). The image is treated as a periodic pattern in both directions.

You can use the `-g` and `-s` options to do gamma correction and change the saturation of the image. Gamma is applied in the RGB color space, saturation is applied in the YUV color space. All other computations are done in the Lab color space.

### Plans

 * TODO: set 38 coloum mode when xshift is not 0 in every line.
 * add separate options for pre-dithering and post-dithering. The idea is that a pre-dithered image is used to optimize colors and post-dithering is used to create the actual output.
 * user defined graphic modes with some examples (eg. some c64 modes)
 * add more graphic modes, including character modes with optimized charset to approximate the picture with 256 chars.

### Credits

 * I have stolen the RGB-to-Lab conversion routine from Larry's fantastic converter's disassembled source code. :)
 * I use IstvanV's great P4FliConverter's DFLI viewer routine as it is.
 * Two custom palettes were generated by www.colodore.com.
