export const IBNSGrade = [
    'UNCIRCULATED',
    'ABOUT_UNCIRCULATED',
    'EXTREMELY_FINE',
    'VERY_FINE',
    'FINE',
    'VERY_GOOD',
    'GOOD',
    'FAIR',
    'POOR'
];

export const InputFeatures = {
    paper: ['FIRM', 'CRISP', 'SOME_SOFTNESS_WRINKLED', 'LIMP', 'TOTALLY_LIMP'],
    colour: [
        'NO_DISCOLOURATION',
        'SMUDGING',
        'CLEAR_BUT_NOT_BRIGHT',
        'SOME_DISCOLOURATION',
        'EXCESSIVE_DISCOLOURATION'
    ],
    corners: [
        'SHARP_AND_SQUARE',
        'SLIGHTEST_ROUNDING',
        'WORN_BUT_NOT_ROUNDED',
        'WORN_AND_ROUNDED',
        'ROUNDED_OR_MISSING'
    ],
    sheen: ['ORIGINAL_SHEEN', 'LOST_SHEEN'],
    foilFeatures: [
        'MINOR_SCRATCHES_MANUFACTURE',
        'NUMEROUS_SCRATCHES',
        'MANY_SCRATCHES_DAMAGE',
        'DAMAGED_FOLDS_BROKEN_SURFACE',
        'SIGNIFICANTLY_DAMAGED'
    ],
    wrinkles: ['NO_WRINKLES', 'FEW_WRINKLES', 'PRESENT'],
    folds: [
        'NO_FOLDS',
        'ONE_LIGHT_FOLD',
        'UP_TO_3_LIGHT_FOLDS',
        'MORE_THAN_3_LIGHT_FOLDS',
        'MANY_FOLDS'
    ],
    creases: ['NO_CREASES', 'ONE_CREASE', 'MORE_THAN_1_CREASE', 'MANY_CREASES'],
    handling: [
        'NO_HANDLING',
        'MINOR',
        'LIGHT',
        'SIGNIFICANT',
        'CONSIDERABLE',
        'HEAVY'
    ],
    wear: ['NO_WEAR', 'IMPERCEPTIBLE', 'SHOWS_WEAR', 'CONSIDERABLE', 'DAMAGED_PAPER'],
    dirt: [
        'NO_DIRT',
        'MINIMAL',
        'NO_EXCESSIVE_DIRT',
        'DIRT_PRESENT',
        'EXCESSIVE_DIRT'
    ],
    stains: ['NO_STAINS', 'STAINS_PRESENT'],
    rust: ['NO_RUST', 'RUST_PRESENT'],
    tears: ['NO_TEARS', 'MINOR_MARGINS_ONLY', 'MINOR_INTO_DESIGN', 'LARGE_TEARS'],
    holes: [
        'NO_HOLES',
        'CENTER_HOLE_ONLY',
        'CENTER_AND_INTERSECTIONS',
        'LARGE_HOLES'
    ],
    piecesMissing: [
        'NO_PIECES',
        'SMALL_PIECE_MISSING',
        'LARGE_PIECE_MISSING',
        'MULTIPLE_PIECES_MISSING'
    ],
    staplePinHoles: ['NONE', 'ONE_TWO_HOLES', 'MULTIPLE_HOLES'],
    graffiti: ['NO_GRAFFITI', 'GRAFFITI_PRESENT']
};

export const formatLabel = (text) => {
    const result = text.replace(/([A-Z])/g, ' $1');
    return result.charAt(0).toUpperCase() + result.slice(1);
};

export const parseMissingInput = (rawString) => {
    try {
        const cleanString = rawString.replace('INPUT:', '');
        const [feature, value] = cleanString.split('.');

        const formatFeature = feature.replace(/([A-Z])/g, ' $1').trim();
        const formatValue = value.replace(/_/g, ' ').toLowerCase();

        return {
            feature: formatFeature,
            value: formatValue.charAt(0).toUpperCase() + formatValue.slice(1)
        };
    } catch (e) {
        return { feature: 'Requirement', value: rawString };
    }
};

export const parseGradingRequirement = (rawReq) => {
    if (!rawReq) return { type: 'UNKNOWN', label: 'Unknown', value: '' };

    const featureMap = {
        creases: 'Creases',
        folds: 'Folds',
        wrinkles: 'Wrinkles',
        paper: 'Paper',
        dirt: 'Dirt',
        stains: 'Stains',
        graffiti: 'Graffiti',
        rust: 'Rust',
        piecesmissing: 'Pieces Missing',
        pieces: 'Pieces Missing',
        staplepinholes: 'Staple/Pin Holes',
        staples: 'Staple/Pin Holes',
        tears: 'Tears',
        holes: 'Holes',
        foilfeatures: 'Foil Features',
        handling: 'Handling',
        wear: 'Wear',
        colour: 'Colour'
    };

    let currentReq = rawReq.trim();

    if (currentReq.startsWith('INPUT:INPUT:')) {
        currentReq = currentReq.replace('INPUT:INPUT:', 'INPUT:');
    }

    if (currentReq.startsWith('ALL:') || currentReq.startsWith('ANY:')) {
        const type = currentReq.startsWith('ALL:')
            ? 'GROUP (ALL)'
            : 'GROUP (ANY)';
        const content = currentReq.substring(4);
        return { type: 'GROUP', label: type, value: content };
    }

    if (currentReq.startsWith('LIMIT:')) {
        const value = currentReq.replace('LIMIT:', '').replace(/_/g, ' ');
        return {
            type: 'LIMIT',
            label: 'Limitation',
            value: value.charAt(0).toUpperCase() + value.slice(1).toLowerCase()
        };
    }
    if (currentReq.startsWith('STATUS:')) {
        const value = currentReq.replace('STATUS:', '').replace(/_/g, ' ');
        return {
            type: 'STATUS',
            label: 'Global Status',
            value: value.charAt(0).toUpperCase() + value.slice(1).toLowerCase()
        };
    }

    if (
        currentReq.includes('NOT:') ||
        currentReq.includes('AND') ||
        currentReq.includes('OR')
    ) {
        let cleanValue = currentReq
            .replace(/NOT:LIMIT:/g, 'No limit ')
            .replace(/AND/g, 'and')
            .replace(/_/g, ' ');
        return { type: 'LIMIT', label: 'Limit Check', value: cleanValue };
    }

    if (currentReq.includes('==')) {
        let [feature, value] = currentReq.split('==');
        let rawFeature = feature.trim().toLowerCase();
        let cleanValue = value
            .replace(/\s+/g, '')
            .replace(/_/g, ' ')
            .toLowerCase();

        const label = featureMap[rawFeature] || feature.trim();
        const finalValue =
            cleanValue.charAt(0).toUpperCase() + cleanValue.slice(1);

        return { type: 'INPUT', label, value: finalValue };
    }

    const isInputPrefix = currentReq.startsWith('INPUT:');
    const isDerivedPrefix = currentReq.startsWith('DERIVED:');

    if (isInputPrefix || isDerivedPrefix) {
        const clean = currentReq.replace('INPUT:', '').replace('DERIVED:', '');
        const [feature, value] = clean.split('.');

        const rawFeature = feature.trim().toLowerCase();
        const label =
            featureMap[rawFeature] ||
            feature.replace(/([A-Z])/g, ' $1').trim();
        const formatValue = value ? value.replace(/_/g, ' ').toLowerCase() : '';

        return {
            type: isInputPrefix ? 'INPUT' : 'DERIVED',
            label,
            value: formatValue
                ? formatValue.charAt(0).toUpperCase() + formatValue.slice(1)
                : ''
        };
    }

    const words = currentReq.split(/\s+/);
    const firstWordLower = words[0].toLowerCase();

    if (featureMap[firstWordLower]) {
        const label = featureMap[firstWordLower];
        const value = words.slice(1).join(' ');
        return {
            type: 'INPUT',
            label,
            value: value || 'Required'
        };
    }

    return { type: 'OTHER', label: 'Condition', value: currentReq };
};