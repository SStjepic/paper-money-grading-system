package com.sbnz.model.models;

import lombok.Data;

import com.sbnz.model.enums.InputFeatures.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Banknote {
    private String id;

    private Paper paper;
    private Colour colour;
    private Corners corners;
    private Sheen sheen;
    private FoilFeatures foilFeatures;
    private Wrinkles wrinkles;
    private Folds folds;
    private Creases creases;
    private Handling handling;
    private Wear wear;
    private Dirt dirt;
    private Stains stains;
    private Rust rust;
    private Tears tears;
    private Holes holes;
    private PiecesMissing piecesMissing;
    private StaplePinHoles staplePinHoles;
    private Graffiti graffiti;
}
