package com.sbnz.service.dtos;

import com.sbnz.model.enums.InputFeatures;
import lombok.Data;

@Data
public class BanknoteGradingRequestDTO {
    private String id;
    private InputFeatures.Paper paper;
    private InputFeatures.Colour colour;
    private InputFeatures.Corners corners;
    private InputFeatures.Sheen sheen;
    private InputFeatures.FoilFeatures foilFeatures;
    private InputFeatures.Wrinkles wrinkles;
    private InputFeatures.Folds folds;
    private InputFeatures.Creases creases;
    private InputFeatures.Handling handling;
    private InputFeatures.Wear wear;
    private InputFeatures.Dirt dirt;
    private InputFeatures.Stains stains;
    private InputFeatures.Rust rust;
    private InputFeatures.Tears tears;
    private InputFeatures.Holes holes;
    private InputFeatures.PiecesMissing piecesMissing;
    private InputFeatures.StaplePinHoles staplePinHoles;
    private InputFeatures.Graffiti graffiti;
}
